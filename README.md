Sentiment Analysis Backend API

This project is a machine learning based sentiment analysis system deployed as a REST API using FastAPI. The API predicts whether a given text expresses positive or negative sentiment.

The backend is deployed on Render and can be accessed publicly.

Live Deployment
Base URL
https://sentimentanalysis-backend-zkqx.onrender.com

API Documentation (Swagger UI)
https://sentimentanalysis-backend-zkqx.onrender.com/docs

Tech Stack
Python
FastAPI
Scikit-learn
TF-IDF Vectorizer
Logistic Regression
Gunicorn
Render

Project Structure
backend.py – FastAPI application and API routes
lr.py – Logistic Regression related logic
tfidf.py – Text preprocessing and vectorization
model.pkl – Trained sentiment analysis model
vectorizer.pkl – Trained TF-IDF vectorizer
requirements.txt – Python dependencies

API Endpoint

POST /predict

Request Body
{
"text": "I love this product"
}

Response
{
"sentiment": "positive"
}

How It Works
The input text is transformed using a pre-trained TF-IDF vectorizer.
The transformed vector is passed to a Logistic Regression model.
The model predicts the sentiment and returns the result as JSON.

Running Locally

Clone the repository
git clone <repository-url>
cd sentiment-backend

Install dependencies
pip install -r requirements.txt

Run the server
uvicorn backend:app --reload

Open Swagger UI
http://127.0.0.1:8000/docs

Deployment
The backend is deployed on Render as a Python Web Service using Gunicorn with Uvicorn workers.

Start Command used on Render
gunicorn backend:app -w 4 -k uvicorn.workers.UvicornWorker

Notes
This repository contains only the backend API.
The frontend (Java-based) runs separately and consumes this API via HTTP requests.

Author
Khushi Ozha
