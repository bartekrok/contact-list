import { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import EditForm from "./EditForm";

const Contact = (props) => { // Komponent Contact pokazuje dane szczegolowe kontaktu, pozwala na usuwanie i edycje kontaktu
  const [contact, setContact] = useState([]);
  const [showDetails, setShowDetails] = useState(false);
  const [isEdit, setIsEdit] = useState(false);
  const token = useSelector((state) => state.auth.token);
  const navigate = useNavigate();

  useEffect(() => {   
    axios(`http://localhost:8080/contacts/${props.id}`, {}) // Pobranie danych kontaktu z bazy danych
      .then((response) => response.data)
      .then((data) => {
        setContact(data);
      });
  }, []);

  const handleOnClick = () => {
    setShowDetails(!showDetails); // Pokazanie szczegolow kontaktu
  };

  const handleEdit = () => { // Edycja kontaktu
    setIsEdit(!isEdit);
  };

  const handleDelete = () => {
    axios(`http://localhost:8080/contactsAdmin/${props.id}`, { // Usuniecie kontaktu z bazy danych
      method: "delete",
      headers: {
        Authorization: `Bearer ${token}`,
      },
    })
      .then((response) => response.data)
      .then((data) => {
        console.log("Delete successful: ", data);
        navigate("/confirmation");
      })
      .catch((error) => console.log(error));
  };

  return (
    <div>
      <h3 onClick={handleOnClick}> 
        Full Name: {contact.name} {contact.lastName}
      </h3>
      {showDetails ? (
        <div>
          <ul>
            <li key={"email"}>email: {contact.email}</li>
            <li key={"password"}>password: {contact.password}</li>
            <li key={"category"}>category: {contact.category}</li>
            <li key={"subcategory"}>subcategory: {contact.subcategory}</li>
            <li key={"phone number"}>phone number: {contact.phoneNumber}</li>
            <li key={"birthDate"}>birthDate: {contact.birthDate}</li>
          </ul>

          {token ? (
            <div>
              <button onClick={handleEdit}>Edit</button>
              {isEdit ? (
                <EditForm id={contact.id}
                name={contact.name}
                lastName={contact.lastName}
                password={contact.password}
                email={contact.email}
                category={contact.category}
                subcategory={contact.subcategory}
                phoneNumber={contact.phoneNumber}
                birthDate={contact.birthDate} />
              ) : null}
              <button onClick={handleDelete}>Delete</button>
            </div>
          ) : null}
        </div>
      ) : null}
    </div>
  );
};

export default Contact;
