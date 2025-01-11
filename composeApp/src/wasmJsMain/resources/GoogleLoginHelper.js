function initializeGoogleLogin(clientId, callback) {
  google.accounts.id.initialize({
    client_id: clientId,
    callback: (response) => {
        callback(response.credential);
    }
  });
  google.accounts.id.prompt();
};
