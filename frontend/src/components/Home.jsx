import { Link } from "react-router-dom";
import ContactList from "./ContactList";
import { useSelector } from "react-redux";
import { useDispatch } from "react-redux";
import { deleteToken } from "../slices/AuthSlice.js";

const Home = () => { // Strona glowna aplikacji ktora wyswietla liste kontaktow i umozliwia przejscie do zalogowania sie
    const token = useSelector((state) => state.auth.token);
    const dispatch = useDispatch();

    const handleLogout = () => {
        dispatch(deleteToken());
    }

    return (
        <div>
            <h1>Home</h1>
            {!token ? (
            <Link to="/login">
                <button>Login</button>
            </Link>) : <Link to="/logout"><button onClick={handleLogout}>Logout</button></Link>}
            <ContactList />
        </div>
    );
};

export default Home;
