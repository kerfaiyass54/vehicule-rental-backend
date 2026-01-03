import pandas as pd
import numpy as np
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import OneHotEncoder, StandardScaler
from sklearn.ensemble import RandomForestClassifier
import joblib


df = pd.read_csv('../utils/password.csv')
df['length'] = df['password'].apply(len)
df['digits'] = df['password'].apply(lambda x: sum(c.isdigit() for c in x))
df['upper'] = df['password'].apply(lambda x: sum(c.isupper() for c in x))
df['special'] = df['password'].apply(lambda x: sum(not c.isalnum() for c in x))

encoder = OneHotEncoder()
cat_encoded = encoder.fit_transform(df[['category']]).toarray()
cat_df = pd.DataFrame(cat_encoded, columns=encoder.get_feature_names_out(['category']))

X = pd.concat([df[['length','digits','upper','special']], cat_df], axis=1)
y = df['strength']  # target variable

X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)
scaler = StandardScaler()
X_train_scaled = scaler.fit_transform(X_train)
X_test_scaled = scaler.transform(X_test)

model = RandomForestClassifier(n_estimators=100, random_state=42)
model.fit(X_train_scaled, y_train)

joblib.dump(model, 'models/password_strength_model.pkl')
joblib.dump(scaler, 'models/scaler.pkl')
joblib.dump(encoder, 'models/encoder.pkl')

