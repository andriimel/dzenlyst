import {setGlobalOptions} from "firebase-functions";
import {onRequest} from "firebase-functions/v2/https";
import * as logger from "firebase-functions/logger";
import * as dotenv from "dotenv";
import {GoogleGenerativeAI} from "@google/generative-ai";

dotenv.config();
setGlobalOptions({maxInstances: 10});

export const geminiChat = onRequest({timeoutSeconds: 60}, async (req, res) => {
  try {
    const prompt = req.body.prompt;
    if (!prompt) {
      res.status(400).send("Missing prompt");
      return;
    }

    const apiKey = process.env.GEMINI_API_KEY;
    if (!apiKey) {
      logger.error("GEMINI_API_KEY is not set");
      res.status(500).send("Server configuration error");
      return;
    }

    const genAI = new GoogleGenerativeAI(apiKey);
    const model = genAI.getGenerativeModel({model: "gemini-pro"});

    const result = await model.generateContentStream({
      contents: [{role: "user", parts: [{text: prompt}]}],
    });

    // Set headers for streaming
    res.setHeader("Content-Type", "text/event-stream");
    res.setHeader("Cache-Control", "no-cache");
    res.setHeader("Connection", "keep-alive");

    for await (const chunk of result.stream) {
      const text = chunk.text();
      if (text) {
        res.write(`data: ${text}\n\n`);
      }
    }

    res.end();
  } catch (error) {
    logger.error("Error calling Gemini Streaming API:", error);
    res.status(500).send("Internal Server Error");
  }
});
