import { initializeApp } from "firebase/app";
import { getAnalytics } from "firebase/analytics";
import {
    getAuth,
    GoogleAuthProvider,
    createUserWithEmailAndPassword,
    signInWithEmailAndPassword,
    signInWithCredential,
    sendEmailVerification,
    signOut,
    onAuthStateChanged
} from "firebase/auth";
import { firebaseConfig } from "./firebaseConfig";

const firebaseApp = initializeApp(firebaseConfig);
const analytics = getAnalytics(firebaseApp);
const provider = new GoogleAuthProvider();
const auth = getAuth();

let unsubscribe;

export async function createUser(email, password){
    const userCred = await createUserWithEmailAndPassword(auth, email, password);

    await sendEmailVerification(userCred.user)

    return {
            uid: userCred.user.uid,
            email: userCred.user.email,
            displayName: userCred.user.email,
            emailVerified: userCred.user.emailVerified,
            accessToken: userCred.user.accessToken
        };
}

export async function loginUserWithPassword(email, password){
    const userCred = await signInWithEmailAndPassword(auth, email, password);
    return {
            uid: userCred.user.uid,
            email: userCred.user.email,
            displayName: userCred.user.email,
            emailVerified: userCred.user.emailVerified,
            accessToken: userCred.user.accessToken
        };
}

export async function loginUserWithGoogle(tokenId){
    var credential = GoogleAuthProvider.credential(tokenId);

    const userCred = await signInWithCredential(auth, credential);
    return {
            uid: userCred.user.uid,
            email: userCred.user.email,
            displayName: userCred.user.email,
            emailVerified: userCred.user.emailVerified,
            accessToken: userCred.user.accessToken
        };
}

export function authStateChanged(callback) {
    if (unsubscribe) {
        unsubscribe(); // Ensure only one listener is active
    }

    unsubscribe = onAuthStateChanged(auth, async (user) => {
        if (user) {
            try {
                const accessToken = await user.getIdToken();
                callback({
                    uid: user.uid,
                    email: user.email,
                    displayName: user.displayName,
                    emailVerified: user.emailVerified,
                    accessToken
                });
            } catch (error) {
                console.error("Error fetching ID token:", error);
                callback(null);
            }
        } else {
            callback(null);
        }
    });
}

export function clearAuthStateListener() {
    if (unsubscribe) {
        unsubscribe();
    }
}

export async function resendEmail(errorCallback){
    try {
        const user = await auth.currentUser
        await sendEmailVerification(user)
    } catch(error) {
        errorCallback(error.message)
    }
}

export async function signUserOut(){
    await signOut(auth);
}
