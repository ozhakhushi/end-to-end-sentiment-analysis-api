from fastapi import FastAPI
from pydantic import BaseModel
import pickle

app = FastAPI()

# LOAD VECTORIZER
with open("vectorizer.pkl", "rb") as f:
    vectorizer = pickle.load(f)

# LOAD MODEL
with open("model.pkl", "rb") as f:
    model = pickle.load(f)


class InputText(BaseModel):
    text: str


@app.post("/predict")
def predict_sentiment(data: InputText):

    X = vectorizer.transform([data.text])
    pred = model.predict(X)[0]

    # RETURN CLEAN JSON
    return {"sentiment": str(pred)}

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000)
