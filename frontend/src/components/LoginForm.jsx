import { Formik, Form, Field, ErrorMessage } from "formik";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { useDispatch } from "react-redux";
import { saveToken } from "../slices/AuthSlice.js";

const LoginForm = () => { // Strona do logowania 
    const dispatch = useDispatch(); // Do logowania wykorzsytujemy token ktory jest zapisany w stanie aplikacji
    const navigate = useNavigate();

    return (
        <div>
            <h1>Login Page</h1>
            <Formik
                initialValues={{ login: "", password: "" }}
                validate={(values) => {
                    const errors = {};
                    if (!values.login) {
                        errors.login = "Required";
                    }
                    if (!values.password) {
                        errors.password = "Required";
                    }
                    return errors;
                }}
                onSubmit={(values) => {
                    axios
                        .post("http://localhost:8080/token", {
                            username: values.login,
                            password: values.password,
                        })
                        .then((response) => response.data)
                        .then((data) => {
                            dispatch(saveToken({token: data}));
                            navigate("/");
                        })
                        .catch((error) => console.log(error));
                }}
            >
                {({ errors }) => (
                    <Form>
                        <Field
                            name="login"
                            type="text"
                            placeholder="login..."
                        />
                        <ErrorMessage name="login" component="div" />
                        <Field
                            name="password"
                            type="password"
                            placeholder="password..."
                        />
                        <ErrorMessage name="password" component="div" />
                        <button
                            type="submit"
                            disabled={!!errors.login || !!errors.password}
                        >
                            Submit
                        </button>
                    </Form>
                )}
            </Formik>
        </div>
    );
};

export default LoginForm;
