# 📱 Netflix App (Apps Script API)

## 📌 Descripció
Aplicació Android desenvolupada en Kotlin que consumeix una API pròpia creada amb Google Apps Script per gestionar dades de contingut tipus Netflix.

L'aplicació permet consultar dades, filtrar-les i afegir nous registres, així com gestionar usuaris amb sistema de login i registre.

---

## 🗂️ Dataset
S'ha utilitzat el dataset de Kaggle:

https://www.kaggle.com/datasets/shivamb/netflix-shows

Aquest dataset s'ha importat a un document de Google Sheets amb dues pestanyes:
https://docs.google.com/spreadsheets/d/101FtRCdNexW_fpLUMuRzqf1_KEo5-CMgQcCk3hjmZaY/edit?usp=sharing
- `netflix` → conté el dataset original
- `new_titles` → conté els nous registres inserits des de l'app (POST)

---

## 🌐 API (Google Apps Script)

S'ha desenvolupat una API amb Google Apps Script que treballa sobre el Google Sheets.

### 🔐 Autenticació
La API requereix una API KEY per validar les peticions: `API_KEY=netflix123`.
La clau es guarda com a variable d'entorn a Apps Script.

---

## 🔗 Endpoints

### Obtenir tots els títols
https://script.googleusercontent.com/macros/echo?user_content_key=AY5xjrQ4Nv-6y9oxtAC77AchVFVeox7QhfJBK62qJP8ePKhLEUb04c-XfnoXnqGtkeM0oXMzwqVTO4Q-XSq-wWET4BednNuyyJ2kHKKZo7AJH_jQ7-GomqEXDXMuXN2SudrQrpaRLmWC7Cpk0Kw7p98MxLSJYbzixn9BdapUWiZw4YVFHNNmALI8wF7hpGgiRg5WV7ujacephPNIH9cGQ8s0xQz1N_JHmv-fkc2K1QP-eN8priWBlsJExBT_5V0doMdNnT8z9QUWxaPmND0s-7qkIDCwgk27qixeOxlhbdnviIqD-S2LkT0-PzKwQbLcxJnQD2EwriS3xrvDSDqNl8Y&lib=MpFVtNd0xBegFqyTf6MqbKXe2hXZ3yIEE

### Filtrar per tipus
https://script.google.com/macros/s/AKfycbxZ-CN8CrodYQF5ndTonzuWgwo82Z7dValc4riq84VdC-KA86rJKJCROMdYum1tqqI_dQ/exec?action=getByType&type=Movie&api_key=netflix123

### Obtenir per ID
https://script.google.com/macros/s/AKfycbxZ-CN8CrodYQF5ndTonzuWgwo82Z7dValc4riq84VdC-KA86rJKJCROMdYum1tqqI_dQ/exec?action=getById&id=s1&api_key=netflix123

### Inserir un nou títol
POST (body JSON)


---

## 📱 Aplicació Android

### 🧩 Arquitectura
El projecte segueix arquitectura MVVM:

- ViewModel
- LiveData
- Repository
- Retrofit

---

## 🔐 Autenticació d'usuaris

L'app permet:

- Registrar usuaris
- Fer login

Les credencials (usuari i contrasenya) es guarden localment mitjançant **SharedPreferences**.

La gestió es fa a través de:
- `AuthViewModel`
- `SharedPrefsManager`

---

## 🔄 Consum de la API

L'app fa servir Retrofit per fer peticions HTTP:

- GET → obtenir dades del Google Sheets
- POST → inserir nous títols a la pestanya `new_titles`

A totes les peticions s'envia el paràmetre: `API_KEY`


---

## ⚙️ Configuració

Fitxer `secrets.properties`:
BASE_URL=https://script.google.com/
API_KEY=netflix123


---

## 📱 Funcionalitats

- Login d'usuari
- Registre d'usuari
- Consulta de dades des de la API (GET)
- Filtrat de dades
- Inserció de noves dades (POST)
- Consum d'una API pròpia amb autenticació


---
## 🎥 Vídeo demostració
En aquets enllaç podeu veure el funcionament de l'aplicació. 
[Video Demostració NetflixApp](https://drive.google.com/file/d/18uNzmRZcYU-WIx38WUNX02wkzbc82ik0/view?usp=share_link)


