import { Formik, Form, Field, ErrorMessage } from "formik"; // Formik to biblioteka do obslugi formularzy
import axios from "axios"; // axios to biblioteka do wykonywania zapytan do serwera
import { useSelector } from "react-redux"; // useSelector pozwala na dostep do stanu aplikacji
import { useNavigate } from "react-router-dom"; // useNavigate pozwala na nawigacje w aplikacji
import { useEffect } from "react"; // useEffect pozwala na wykonywanie kodu w odpowiednim momencie zycia komponentu

const AddForm = () => {
  const token = useSelector((state) => state.auth.token);
  const navigate = useNavigate();
  const emails = [];
  const categories = ["Work", "Private", "Other"];
  const subcategories = [
    "Boss",
    "Employee",
    "Collegue",
    "Friend",
    "Family",
    "Enemy",
  ];
  const fetchContent = () => {             // Wykonanie zapytania do serwera i pobranie danych, aby zapewnic unikalnosc emaili
    axios(`http://localhost:8080/contacts`)
      .then((response) => response.data)
      .then((data) => {
        data.map((contact) => {
          emails.push(contact.email);
        });
      });
  };
  useEffect(() => {
    fetchContent();
  }, []);

  return (
    <div>
      <Formik                              // Stworzenie formularza ktore bedzie walidowal pola
        initialValues={{                   // oraz wrzucal je za pomoca POST do bazy danych
          name: "",
          lastName: "",
          email: "",
          password: "",
          category: "",
          subcategory: "",
          phoneNumber: "",
          birthDate: "",
        }}
        validate={(values) => {
          const errors = {};
          const passwordRegex = new RegExp( // Regex do walidacji hasla
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])(?=.{8,})"
          );
          const phoneNubmerRegex = new RegExp("^[0-9]{9}$"); // Regex do walidacji numeru telefonu

          if (!values.name) {             // Walidacja pola name, jezeli jest puste to prosimy o uzupelnienie
            errors.name = "Required";
          }
          if (!values.lastName) {
            errors.lastName = "Required";
          }
          if (!values.email) {
            errors.email = "Required";
          }

          if (emails.includes(values.email)) { // Walidacja pola email, jezeli email juz istnieje w bazie danych to prosimy o dodanie innego
            errors.email = "Email already exists";
          }

          if (!values.password) {
            errors.password = "Required";
          }
          if (!passwordRegex.test(values.password)) { // Walidacja pola password, jezeli haslo nie spelnia wymagan to prosimy o dodanie innego
            errors.password = "Wrong password format";
          }
          if (!values.category) {
            errors.category = "Required";
          }
          if (!values.subcategory) {
            errors.subcategory = "Required";
          }
          if (!values.phoneNumber) {
            errors.phoneNumber = "Required";
          }
          if (!phoneNubmerRegex.test(values.phoneNumber)) { // Walidacja pola phoneNumber, jezeli numer telefonu nie spelnia wymagan to prosimy o dodanie innego
            errors.phoneNumber = "Wrong phone number";
          }
          if (!values.birthDate) {
            errors.birthDate = "Required";
          }
          return errors;
        }}
        onSubmit={(values) => {         // Wyslanie danych do bazy danych za pomoca POST
          axios(`http://localhost:8080/contactsAdmin`, {
            method: "post",
            headers: {
              Authorization: `Bearer ${token}`,
            },
            data: {
              name: values.name,
              lastName: values.lastName,
              email: values.email,
              password: values.password,
              category: values.category,
              subcategory: values.subcategory,
              phoneNumber: values.phoneNumber,
              birthDate: values.birthDate,
            },
          })
            .then((response) => response.data)
            .then((data) => {
              navigate("/confirmation");
            })
            .catch((error) => console.log(error));
        }}
      >
        {({ errors }) => (      // Formularz z polami do uzupelnienia oraz przyciskiem do wyslania danych, owy form wykorzsytuje wszystkie funkcje wypisane powyzej
          <Form>                    
            <Field name="name" type="text" placeholder="name" />
            <ErrorMessage name="name" component="div" />
            <Field name="lastName" type="text" placeholder="lastName" />
            <ErrorMessage name="lastName" component="div" />
            <Field name="email" type="email" placeholder="email" />
            <ErrorMessage name="email" component="div" />
            <Field name="password" type="text" placeholder="password" />
            <ErrorMessage name="password" component="div" />
            <Field as="select" name="category" placeholder="category">
              <option disabled value="">
                Select category
              </option>
              {categories.map((category) => (
                <option value={category}>{category}</option>
              ))}
            </Field>
            <ErrorMessage name="category" component="div" />
            <Field as="select" name="subcategory" placeholder="subcategory">
              <option disabled value="">
                Select subcategory
              </option>
              {subcategories.map((subcategory) => (
                <option value={subcategory}>{subcategory}</option>
              ))}
            </Field>
            <ErrorMessage name="subcategory" component="div" />
            <Field name="phoneNumber" type="number" placeholder="phoneNumber" />
            <ErrorMessage name="phoneNumber" component="div" />
            <Field name="birthDate" type="date" placeholder="birthDate" />
            <ErrorMessage name="birthDate" component="div" />

            <button
              type="submit"
              disabled={
                !!errors.name ||
                !!errors.lastName ||
                !!errors.email ||
                !!errors.password ||
                !!errors.category ||
                !!errors.subcategory ||
                !!errors.phoneNumber ||
                !!errors.birthDate
              }
            >
              Submit
            </button>
          </Form>
        )}
      </Formik>
    </div>
  );
};

export default AddForm;
