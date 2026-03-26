<h1>OpenWeather API Automation Testing</h1>
<h3>Katalon Studio Project</h3>

<p>
API automation testing for OpenWeather using <b>Katalon Studio</b><br>
Covers Forecast & Air Pollution endpoints
</p>

</div>

<hr>

<h2>📌 Project Overview</h2>

<p>
This project contains API automation testing for OpenWeather.
</p>

<ul>
  <li>5-day weather forecast (Jakarta Selatan)</li>
  <li>Current air pollution data (Jakarta Selatan)</li>
</ul>

<p>
The automation uses a <b>chained API approach</b>:
</p>

<ol>
  <li>Get latitude & longitude from Geocoding API</li>
  <li>Use the coordinates for:
    <ul>
      <li>Forecast API</li>
      <li>Air Pollution API</li>
    </ul>
  </li>
</ol>

<p>
This ensures real-world API integration validation instead of testing endpoints in isolation.
</p>

<hr>

<h2>🛠 Tech Stack</h2>

<table>
<tr>
<td>Katalon Studio</td>
<td>Groovy</td>
<td>REST API</td>
<td>OpenWeather API</td>
</tr>
</table>

<hr>

<h2>📂 Project Structure</h2>

<pre>
OpenWeather-Katalon/
│
├── Test Cases/
│   ├── TC_Geocoding
│   ├── TC_Forecast
│   └── TC_AirPollution
│
├── Test Suites/
│   └── TS_OpenWeather
│
├── Object Repository/
│   ├── Geocoding_Request
│   ├── Forecast_Request
│   └── AirPollution_Request
│
├── Profiles/
│   └── default (API_KEY, lat, lon)
│
└── Reports/
</pre>

<hr>

<hr>

<h2>🧪 Test Scenarios</h2>

<h3>✅ Positive Scenario</h3>

<table>
<tr>
<th>ID</th>
<th>Scenario</th>
<th>Expected</th>
</tr>

<tr>
<td>TC-01</td>
<td>Get geocoding data</td>
<td>Status 200 + lat/lon returned</td>
</tr>

<tr>
<td>TC-02</td>
<td>Get forecast with valid lat/lon</td>
<td>Data returned</td>
</tr>

<tr>
<td>TC-03</td>
<td>Get air pollution with valid lat/lon</td>
<td>Data returned</td>
</tr>

</table>


<hr>

<h2>🔍 Assertions Implemented</h2>

<h3>📍 Geocoding API</h3>

<h4>Basic Validation</h4>
<ul>
  <li>Status code = 200</li>
</ul>

<h4>Data Extraction</h4>
<ul>
  <li>Extract <code>lat</code> from response</li>
  <li>Extract <code>lon</code> from response</li>
</ul>

<hr>

<h3>🌤 Forecast API</h3>

<h4>Basic Validation</h4>
<ul>
  <li>Status code = 200</li>
  <li><code>cod = "200"</code></li>
  <li><code>list.size() > 0</code></li>
</ul>

<h4>Field Validation</h4>
<ul>
  <li><code>list[0].main.temp != null</code></li>
  <li><code>list[0].weather[0].main != null</code></li>
  <li><code>list[0].dt_txt != null</code></li>
</ul>

<h4>Data Validation</h4>
<ul>
  <li><code>list[0].main.temp > 0</code></li>
  <li><code>list[0].main.humidity >= 0 && list[0].main.humidity <= 100</code></li>
</ul>

<hr>

<h3>🌫 Air Pollution API</h3>

<h4>Basic Validation</h4>
<ul>
  <li>Status code = 200</li>
  <li><code>list.size() > 0</code></li>
</ul>

<h4>Field Validation</h4>
<ul>
  <li><code>list[0].main.aqi != null</code></li>
  <li><code>list[0].components.co != null</code></li>
  <li><code>list[0].components.pm2_5 != null</code></li>
</ul>

<h4>Data Validation</h4>
<ul>
  <li><code>list[0].main.aqi >= 1 && list[0].main.aqi <= 5</code></li>
  <li><code>list[0].components.co >= 0</code></li>
</ul>

<hr>

<h2>🔗 Chained Validation</h2>

<p>
This project applies <b>chained API testing</b>:
</p>

<ol>
  <li>Geocoding API is called first to retrieve <code>latitude</code> and <code>longitude</code></li>
  <li>The returned <code>lat</code> and <code>lon</code> are used in the Forecast API request</li>
  <li>The same <code>lat</code> and <code>lon</code> are also used in the Air Pollution API request</li>
</ol>

<p><b>This proves:</b></p>
<ul>
  <li>Understanding of API dependency flow</li>
  <li>Ability to validate real API integration</li>
  <li>Practical end-to-end API testing approach</li>
</ul>

<hr>

<h2>▶️ How to Run</h2>

<h3>Prerequisites</h3>
<ul>
  <li>Install <b>Katalon Studio</b></li>
  <li>Have an <b>OpenWeather API Key</b></li>
</ul>

<h3>Steps</h3>

<ol>
  <li>Open project in <b>Katalon Studio</b></li>
  <li>Update API key in request URL or Profile</li>
  <li>Open:
    <pre>Test Suites → OpenWeather_TestSuite</pre>
  </li>
  <li>Click:
    <pre>▶ Run</pre>
  </li>
</ol>

<hr>

<h2>📊 Test Result</h2>

<p><b>Test execution result:</b></p>

<table>
<tr>
<th>Metric</th>
<th>Value</th>
</tr>

<tr>
<td>Runs</td>
<td>1</td>
</tr>

<tr>
<td>Passes</td>
<td>1</td>
</tr>

<tr>
<td>Failures</td>
<td>0</td>
</tr>

<tr>
<td>Errors</td>
<td>0</td>
</tr>

</table>

<hr>

<h2>🌐 API Endpoints</h2>

<h3>Geocoding</h3>
<pre>
https://api.openweathermap.org/geo/1.0/direct?q=Jakarta%20Selatan&limit=1&appid=YOUR_API_KEY
</pre>

<h3>Forecast</h3>
<pre>
https://api.openweathermap.org/data/2.5/forecast?lat=${lat}&lon=${lon}&appid=YOUR_API_KEY
</pre>

<h3>Air Pollution</h3>
<pre>
http://api.openweathermap.org/data/2.5/air_pollution?lat=${lat}&lon=${lon}&appid=YOUR_API_KEY
</pre>

<hr>

<h2>⚠️ Notes</h2>

<ul>
  <li>API key has been replaced with <code>YOUR_API_KEY</code> for security</li>
  <li>This project is created for technical assessment purposes</li>
</ul>

<hr>

<h2>👩‍💻 Author</h2>

<p><b>Silmi Rahmawati</b></p>

<hr>

<div align="center">
<h3>✨ Thank You for Reviewing This Project ✨</h3>
</div>
