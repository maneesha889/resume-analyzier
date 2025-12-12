<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>AI Resume Analyzer</title>

    <style>
        body {
            background: #f2f4f8;
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }

        .container {
            background: #fff;
            width: 450px;
            padding: 25px;
            border-radius: 10px;
            box-shadow: 0 0 12px rgba(0,0,0,0.15);
        }

        h2 {
            text-align: center;
            margin-bottom: 20px;
            color: #222;
        }

        input[type=file] {
            width: 100%;
            padding: 10px;
            border: 2px solid #ddd;
            border-radius: 6px;
            margin-bottom: 20px;
            cursor: pointer;
        }

        button {
            width: 100%;
            padding: 12px;
            font-size: 16px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            transition: 0.3s ease;
        }

        button:hover {
            background-color: #0056b3;
        }

        #resultBox {
            margin-top: 20px;
            background: #fafafa;
            padding: 15px;
            border-left: 4px solid #007bff;
            border-radius: 6px;
            font-size: 14px;
            height: 260px;
            overflow-y: auto;
            white-space: pre-wrap;
            display: none;
        }

        /* Loading animation */
        .loader {
            border: 4px solid #ddd;
            border-top: 4px solid #007bff;
            border-radius: 50%;
            width: 32px;
            height: 32px;
            animation: spin 0.8s linear infinite;
            margin: 15px auto;
            display: none;
        }

        @keyframes spin {
            0% { transform: rotate(0deg); }
            100% { transform: rotate(360deg); }
        }
    </style>

</head>

<body>

<div class="container">
    <h2>AI Resume Analyzer</h2>

    <form id="uploadForm">
        <input type="file" id="resumeFile" name="file" accept=".pdf,.doc,.docx,.txt" required>
        <button type="submit">Analyze Resume</button>
    </form>

    <div class="loader" id="loading"></div>

    <div id="resultBox"></div>
</div>

<script>
    document.getElementById("uploadForm").addEventListener("submit", async (event) => {
        event.preventDefault();

        let fileInput = document.getElementById("resumeFile");
        let resultBox = document.getElementById("resultBox");
        let loader = document.getElementById("loading");

        if (fileInput.files.length === 0) {
            alert("Please select a resume file first.");
            return;
        }

        let formData = new FormData();
        formData.append("file", fileInput.files[0]);

        // Show loader
        loader.style.display = "block";
        resultBox.style.display = "none";
        resultBox.innerText = "";

        try {
            let response = await fetch("/api/resume/upload", {
                method: "POST",
                body: formData
            });

            let text = await response.text();

            // Hide loader & show output
            loader.style.display = "none";
            resultBox.style.display = "block";
            resultBox.innerText = text;

        } catch (error) {
            loader.style.display = "none";
            resultBox.style.display = "block";
            resultBox.innerText = "Error: " + error.message;
        }
    });
</script>

</body>
</html>
