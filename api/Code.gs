// API KEY
const API_KEY = "netflix123";

// Noms de les pestanyes del Google Sheets
const SHEET_NETFLIX = "netflix";
const SHEET_NEW_TITLES = "new_titles";

// FUNCIÓ DOGET (GET)
function doGet(e) {
  try {
    // Agafem paràmetres de la URL
    const apiKey = e.parameter.api_key;
    const action = e.parameter.action;

    // Comprovem API KEY
    if (apiKey !== API_KEY) {
      return jsonResponse({ error: "API KEY incorrecta" });
    }

    // ENDPOINT 1 → obtenir tot
    if (action === "getAll") {
      return getAllTitles();
    }

    // ENDPOINT 2 → filtrar per tipus
    else if (action === "getByType") {
      const type = e.parameter.type;
      return getTitlesByType(type);
    }

    // ENDPOINT 3 → cercar per ID
    else if (action === "getById") {
      const id = e.parameter.id;
      return getTitleById(id);
    }

    // Si no coincideix cap endpoint
    else {
      return jsonResponse({ error: "Endpoint GET no vàlid" });
    }

  } catch (error) {
    return jsonResponse({ error: error.toString() });
  }
}

// FUNCIÓ DOPOST (POST)
function doPost(e) {
  try {
    // Convertim el JSON rebut en objecte
    const data = JSON.parse(e.postData.contents);

    // Comprovem API KEY
    if (data.api_key !== API_KEY) {
      return jsonResponse({ error: "API KEY incorrecta" });
    }

    const action = data.action;

    // ENDPOINT POST → inserir dades
    if (action === "insert") {
      return insertTitle(data);
    }

    else {
      return jsonResponse({ error: "Endpoint POST no vàlid" });
    }

  } catch (error) {
    return jsonResponse({ error: error.toString() });
  }
}

// GET ALL
function getAllTitles() {
  const sheet = SpreadsheetApp.getActiveSpreadsheet().getSheetByName(SHEET_NETFLIX);

  // Agafem totes les dades
  const data = sheet.getDataRange().getValues();

  const headers = data[0]; // fila 1 (noms columnes)
  const rows = data.slice(1); // resta de files

  // Convertim cada fila a objecte JSON
  const result = rows.map(row => rowToObject(headers, row));

  return jsonResponse(result);
}

// FILTRAR PER TYPE
function getTitlesByType(type) {
  const sheet = SpreadsheetApp.getActiveSpreadsheet().getSheetByName(SHEET_NETFLIX);
  const data = sheet.getDataRange().getValues();

  const headers = data[0];
  const rows = data.slice(1);

  // Filtrar per columna type (índex 1)
  const filtered = rows
    .filter(row => row[1] == type)
    .map(row => rowToObject(headers, row));

  return jsonResponse(filtered);
}

// GET BY ID
function getTitleById(id) {
  const sheet = SpreadsheetApp.getActiveSpreadsheet().getSheetByName(SHEET_NETFLIX);
  const data = sheet.getDataRange().getValues();

  const headers = data[0];
  const rows = data.slice(1);

  // Cercar per show_id (índex 0)
  const found = rows.find(row => row[0] == id);

  if (!found) {
    return jsonResponse({ error: "No trobat" });
  }

  return jsonResponse(rowToObject(headers, found));
}

// INSERT (POST)
function insertTitle(data) {
  const sheet = SpreadsheetApp.getActiveSpreadsheet().getSheetByName(SHEET_NEW_TITLES);

  // Crear nova fila
  const newRow = [
    data.show_id || "",
    data.type || "",
    data.title || "",
    data.director || "",
    data.country || "",
    data.release_year || "",
    data.rating || "",
    data.duration || "",
    data.listed_in || "",
    data.description || ""
  ];

  // Afegir fila al final
  sheet.appendRow(newRow);

  return jsonResponse({
    success: true,
    message: "Inserit correctament"
  });
}

// CONVERTIR FILA A JSON
function rowToObject(headers, row) {
  let obj = {};

  for (let i = 0; i < headers.length; i++) {
    obj[headers[i]] = row[i];
  }

  return obj;
}

// RESPOSTA JSON
function jsonResponse(data) {
  return ContentService
    .createTextOutput(JSON.stringify(data))
    .setMimeType(ContentService.MimeType.JSON);
}