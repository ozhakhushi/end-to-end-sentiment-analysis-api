from sklearn.feature_extraction.text import TfidfVectorizer
import pickle
import pandas as pd
import numpy as np

d = pd.read_csv("train.csv", encoding="latin1")

m = TfidfVectorizer()
m.fit(d["text"].astype(str))

with open("vectorizer.pkl", "wb") as f:
    pickle.dump(m, f)
