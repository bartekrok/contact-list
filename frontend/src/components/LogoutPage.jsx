import { useEffect } from "react";
import { useNavigate } from "react-router-dom";

const LogoutPage = () => { // Strona do wylogowywania
    const navigate = useNavigate();

    useEffect(() => {
        setTimeout(() => {
            navigate("/");
        }, 1000);
    }, []);

    return (<div>Logging out...</div>);
}

export default LogoutPage;