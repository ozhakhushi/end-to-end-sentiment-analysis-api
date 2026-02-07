# End-to-End Sentiment Analysis System (Deployed ML API)

Built and deployed a production-style **sentiment analysis system** that performs **real-time text classification** using TF-IDF and Logistic Regression. The system is exposed via a REST API using FastAPI and achieves approximately **70% accuracy with strong F1 performance**, making it suitable for lightweight, scalable inference.

## 🚀 Live Deployment
**Base URL:**
[https://sentimentanalysis-backend-zkqx.onrender.com](https://sentimentanalysis-backend-zkqx.onrender.com)

**Interactive API Documentation (Swagger UI):**
[https://sentimentanalysis-backend-zkqx.onrender.com/docs](https://sentimentanalysis-backend-zkqx.onrender.com/docs)

## 📌 Overview

Understanding customer sentiment is essential for data-driven product and business decisions. This project provides a deployable machine learning backend capable of classifying text as **positive** or **negative** within milliseconds.

Designed with real-world usability in mind, the system emphasizes:

* low-latency predictions
* clean API design
* production-style deployment
* modular architecture

## 🧠 Problem Statement
Manual sentiment evaluation is time-consuming and impractical at scale. The objective of this project was to design a machine learning system that could:

* Automatically classify sentiment
* Handle real-time requests
* Be easily integrated into external applications
* Maintain strong performance with minimal infrastructure

## ⚙️ Approach
### Data Processing

* Cleaned and normalized raw text
* Removed noise such as punctuation and stopwords
* Converted text into numerical features using **TF-IDF vectorization**

### Model Selection

Multiple models were evaluated to determine the best fit for high-dimensional sparse text data:

* Naive Bayes
* Linear Support Vector Machine
* Logistic Regression

**Final Choice:** Logistic Regression
**Reason:** Demonstrated the most stable balance between precision and recall while remaining computationally efficient for deployment.

### Hyperparameter Tuning

Used **GridSearchCV** to optimize model parameters and improve generalization.

### Deployment Strategy

* Built REST API using **FastAPI**
* Served with **Gunicorn + Uvicorn workers**
* Deployed on **Render** for public access
* Structured for real-time inference workloads

## 📊 Model Evaluation

| Metric    | Score |
| --------- | ----- |
| Accuracy  | ~69%  |
| Precision | ~71%  |
| Recall    | ~69%  |
| F1-score  | ~69%  |


## 🏗 System Architecture

```
Client Application
      ↓
   FastAPI Server
      ↓
TF-IDF Vectorizer
      ↓
Logistic Regression Model
      ↓
   Sentiment Prediction (JSON)
```

---

## 🧰 Tech Stack

**Languages & Frameworks**

* Python
* FastAPI

**Machine Learning**

* Scikit-learn
* TF-IDF Vectorizer
* Logistic Regression

**Deployment**

* Gunicorn
* Uvicorn
* Render

---

## 📂 Project Structure

```
backend.py        → FastAPI application and API routes  
lr.py             → Logistic Regression model logic  
tfidf.py          → Text preprocessing and vectorization  
model.pkl         → Serialized trained model  
vectorizer.pkl    → Serialized TF-IDF vectorizer  
requirements.txt  → Project dependencies  
```

---

## 🔎 API Endpoint

### POST `/predict`

**Request**

```json
{
  "text": "I love this product"
}
```

**Response**

```json
{
  "sentiment": "positive"
}
```

---

## ▶️ Running Locally

Clone the repository:

```bash
git clone <repository-url>
cd sentiment-backend
```

Install dependencies:

```bash
pip install -r requirements.txt
```

Start the server:

```bash
uvicorn backend:app --reload
```

Access Swagger UI:

```
http://127.0.0.1:8000/docs
```

---

## 🌐 Deployment Details

The backend is deployed as a **Python Web Service on Render** using:

```
gunicorn backend:app -w 4 -k uvicorn.workers.UvicornWorker
```

The API is production-ready and designed to power external clients via HTTP requests.
A separate frontend consumes this service for user interaction.

---

## 💡 Key Learnings

* Feature engineering often has greater impact than increasing model complexity
* Linear models perform exceptionally well on sparse NLP datasets
* Model evaluation metrics beyond accuracy are critical for real-world reliability
* Designing ML systems as APIs significantly improves usability
* Deployment introduces practical considerations such as latency, scalability, and fault tolerance

---

## 🔮 Potential Improvements

* Add Docker for containerized deployment
* Implement CI/CD pipeline
* Introduce monitoring & logging
* Expand to multi-class sentiment classification
* Experiment with transformer-based embeddings

---

## 👩‍💻 Author

**Khushi Ozha**
