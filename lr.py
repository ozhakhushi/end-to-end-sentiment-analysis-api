from sklearn.linear_model import LogisticRegression
import pickle
import pandas as pd
from sklearn.metrics import accuracy_score   

d=pd.read_csv("train.csv",encoding="latin1")
texts=d["text"].astype("str")
y=d["sentiment"]

with open("vectorizer.pkl","rb") as f:
    tfidf=pickle.load(f)

x=tfidf.transform(texts)

n=LogisticRegression()
n.fit(x,y)



with open("model.pkl","wb") as f:
    pickle.dump(n,f)
