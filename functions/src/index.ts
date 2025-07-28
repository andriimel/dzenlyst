import {setGlobalOptions} from "firebase-functions";
import {onRequest} from "firebase-functions/https";
import * as logger from "firebase-functions/logger";
import axios from "axios";
import * as dotenv from "dotenv";

dotenv.config();

setGlobalOptions({maxInstances: 10});

export const geminiChat = onRequest(async (req, res) => {
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

    const response = await axios.post(
      `https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent?key=${apiKey}`,
      {
        contents: [{parts: [{text: prompt}]}],
      }
    );

    res.status(200).json(response.data);
  } catch (error) {
    logger.error("Error calling Gemini API:", error);
    res.status(500).send("Internal Server Error");
  }
});
