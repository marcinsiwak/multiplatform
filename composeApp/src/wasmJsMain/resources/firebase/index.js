import { initializeApp } from "firebase/app";
import { getAnalytics } from "firebase/analytics";
import { getAuth, signInWithPopup, GoogleAuthProvider, createUserWithEmailAndPassword } from "firebase/auth";

const firebaseConfig = {
  apiKey: "AIzaSyC6NQgwckIiz7L5S7EVLHidsO8IByB3y_E",
  authDomain: "sportplatform-b5318.firebaseapp.com",
  projectId: "sportplatform-b5318",
  storageBucket: "sportplatform-b5318.firebasestorage.app",
  messagingSenderId: "607279059338",
  appId: "1:607279059338:web:23cf771c199457fdcb3873",
  measurementId: "G-N93CDPCXM5"
};

const firebaseApp = initializeApp(firebaseConfig);
const analytics = getAnalytics(firebaseApp);
const provider = new GoogleAuthProvider();
const auth = getAuth();

export async function createUser(email, password){

    console.log("Logging in!");
    try {
        const userCred = await createUserWithEmailAndPassword(auth, email, password);
        console.log("User created:", userCred.user); // Successfully created user
        return {
                 uid: userCred.user.uid,
                email: userCred.user.email,
                displayName: userCred.user.email,
                emailVerified: userCred.user.emailVerified,
                accessToken: userCred.user.accessToken
            };
    } catch (error) {
        console.error("Error creating user:", error.message);
        throw error; // Re-throw to handle it elsewhere if needed
    }
}
