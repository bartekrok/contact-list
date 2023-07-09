import "./App.css";
import Home from "./components/Home";
import { Routes, Route } from "react-router-dom";
import LoginForm from "./components/LoginForm";
import ConfirmationPage from "./components/ConfirmationPage";
import LogoutPage from "./components/LogoutPage";

function App() {
    return (
        <div>
            <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/login" element={<LoginForm />} />
                <Route path="/confirmation" element={<ConfirmationPage />} />
                <Route path="/logout" element={<LogoutPage />} />
            </Routes>
        </div>
    );
}

export default App;
