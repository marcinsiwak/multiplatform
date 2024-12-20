function initializeGoogleLogin(clientId, callback) {
  google.accounts.id.initialize({
    client_id: clientId,
    callback: (response) => {
        callback(response.credential);
    }
  });
  google.accounts.id.prompt();
};

function handleCredentialResponse(response) {
  console.log('Encoded JWT ID token: ' + response.credential);
}

async function loginGoogle(idToken) {
  try {
    // Create a credential from the ID token
    const credential = GoogleAuthProvider.credential(idToken);

    // Sign in with the credential
    const result = await signInWithCredential(auth, credential);

    // Access user details
    const user = result.user;
    console.log('User logged in:', user);

    return result; // Return user details
  } catch (error) {
    console.error('Error during Google login:', error);
    throw error;
  }
}
