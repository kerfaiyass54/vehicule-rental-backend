from flask import Flask, request, jsonify
import pandas as pd
import joblib

app = Flask(__name__)

# Load model
model = joblib.load('models/password_strength_model.pkl')
scaler = joblib.load('models/scaler.pkl')
encoder = joblib.load('models/encoder.pkl')

def extract_features(password, category='unknown'):
    length = len(password)
    digits = sum(c.isdigit() for c in password)
    upper = sum(c.isupper() for c in password)
    special = sum(not c.isalnum() for c in password)
    cat_encoded = encoder.transform([[category]]).toarray()
    features = pd.DataFrame([[length, digits, upper, special] + cat_encoded.tolist()[0]])
    return scaler.transform(features)

@app.route('/predict', methods=['POST'])
def predict():
    data = request.json
    password = data.get('password')
    category = data.get('category', 'unknown')
    features = extract_features(password, category)
    pred = model.predict(features)[0]
    return jsonify({'strength': int(pred)})

if __name__ == '__main__':
    app.run(port=5000)
