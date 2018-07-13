const path = require('path');
const cfenv = require('cfenv');
const cors = require('cors');
const express = require('express');
const app = express();

const appEnv = cfenv.getAppEnv();
const port = process.env.PORT || 3000;

if (appEnv.isLocal) {
  appEnv.port = port;
  appEnv.urls[0] = `http://localhost:${port}`;
  appEnv.url = `https://localhost:${port}`;
}

app.use(cors());
app.use(express.static(path.resolve(__dirname)));

app.get('/env', (req, res) => {
  res.json({
    ...appEnv,
    productServiceUrl: process.env.PRODUCT_SERVICE_URL,
    uaaServiceUrl: process.env.UAA_SERVICE_URL,
  });
});

app.get('/bg-info', (req, res) => {
  res.json({
    appVersion: process.env.APP_VERSION || null
  })
});

app.get('*', (req, res) => {
  res.sendFile(path.resolve(__dirname, './404.html'));
});


app.listen(process.env.PORT || 3000);
