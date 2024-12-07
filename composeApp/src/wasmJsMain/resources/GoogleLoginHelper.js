function initializeGoogleLogin(callback) {
  google.accounts.id.initialize({
    client_id: '607279059338-3k018la7lvokmc1f1j8u3vr3eqhr7pa0.apps.googleusercontent.com',
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
//
// function handleCredentialResponse(response) {
//     // decodeJwtResponse() is a custom function defined by you
//     // to decode the credential response.
//     const responsePayload = decodeJwtResponse(response.credential);
//
//     console.log("ID: " + responsePayload.sub);
//     console.log('Full Name: ' + responsePayload.name);
//     console.log('Given Name: ' + responsePayload.given_name);
//     console.log('Family Name: ' + responsePayload.family_name);
//     console.log("Image URL: " + responsePayload.picture);
//     console.log("Email: " + responsePayload.email);
//  }

//function renderGoogleButton(elementId, options) {
//    google.accounts.id.renderButton(
//        document.getElementById(elementId),
//        options
//    );
//}
//
//function promptGoogleLogin() {
//    google.accounts.id.prompt();
//}