function setupLocalhost(){
        const apiBaseUrl = location.hostname === 'localhost'
          ? 'http://localhost:8080' // Local backend URL
          : 'https://your-production-backend.com'; // Production backend URL

        // Example: Attach it to the global `window` object
        window.apiBaseUrl = apiBaseUrl;
}