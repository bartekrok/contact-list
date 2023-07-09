import { useEffect } from "react";
import { useNavigate } from "react-router-dom";

const ConfirmationPage = () => {   // Strona potrzebna do zresetowania strony w celu wyswietlenia zedytowanych albo dodanych danych dajac wrazenie dzialania w "czasie rzeczywistym"
    const navigate = useNavigate();
    
    useEffect(() => {
        setTimeout(() => {
        navigate("/");
        }, 1000);
    }, []);
    
    return (
        <div>
        <h1>Thank you!</h1>
        </div>
    );
}

export default ConfirmationPage;
