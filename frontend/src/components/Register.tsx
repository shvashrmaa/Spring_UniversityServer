import React, { useState } from "react";
import { useDispatch } from "react-redux";
import { registerStudent } from "../redux/slices/StudentSlice";
import { AppDispatch } from "../redux/Redux";

interface ChildComponentProps {
  setComponent: React.Dispatch<React.SetStateAction<string>>;
}

const Register: React.FC<ChildComponentProps> = ({ setComponent }) => {
  const dispatch: AppDispatch = useDispatch();
  const [studentInfo, setStudentInfo] = useState({
    name: "",
    email: "",
    password: "",
    confirmPassword: "",
  });

  const onchangeStudentAttributes = (
    e: React.ChangeEvent<HTMLInputElement>
  ) => {
    setStudentInfo({ ...studentInfo, [e.target.name]: e.target.value });
  };

  const onRegisterButtonClicked = (e: React.FormEvent) => {
    e.preventDefault();
    dispatch(registerStudent(studentInfo));
    setStudentInfo({ name: "", password: "", email: "", confirmPassword: "" });
  };

  const onChangeComponentButtonClicked = () => {
    setComponent("Login");
  };
  
  return (
    <div className="w-[100%] bg-white rounded-md flex items-center justify-center flex-col">
      <h1 className="font-bold text-primary-color text-[1.3rem] underline cursor-pointer mb-6">
        Register Acocunt
      </h1>
      <form className="flex flex-col m-3 w-[100%] mb-4">
        <label htmlFor="name" className="font-semibold text-gray-600 mb-1">
          Full Name
        </label>
        <input
          type="text"
          name="name"
          id="name"
          required
          className="py-1 font-semibold text-gray-700 border-b-2 border-gray-300 outline-none mb-2"
          onChange={onchangeStudentAttributes}
          value={studentInfo.name}
        />
        <label htmlFor="email" className="font-semibold text-gray-600 mb-1">
          Email Address
        </label>
        <input
          type="email"
          name="email"
          id="email"
          required
          className="py-1 font-semibold text-gray-700 border-b-2 border-gray-300 outline-none mb-2"
          onChange={onchangeStudentAttributes}
          value={studentInfo.email}
        />
        <label htmlFor="password" className="font-semibold text-gray-600 mb-1">
          Password
        </label>
        <input
          type="password"
          name="password"
          id="password"
          required
          className="py-1 font-semibold text-gray-700 border-b-2 border-gray-300 outline-none mb-2"
          onChange={onchangeStudentAttributes}
          value={studentInfo.password}
        />
        <label
          htmlFor="confirmPassword"
          className="font-semibold text-gray-600 mb-1"
        >
          Confirm Password
        </label>
        <input
          type="password"
          name="confirmPassword"
          id="confirmPassword"
          required
          className="py-1 font-semibold text-gray-700 border-b-2 border-gray-300 outline-none mb-2"
          onChange={onchangeStudentAttributes}
          value={studentInfo.confirmPassword}
        />
      </form>
      <div className="flex flex-row justify-center items-center space-x-2 m-4">
        <input
          type="checkbox"
          name="TermsCheckbox"
          id="TermsCheckbox"
          required
        />
        <label
          htmlFor="TermsCheckbox"
          className="font-semibold text-[.8rem] text-gray-600 mb-1"
        >
          By clicking this you accepted all terms & conditions
        </label>
      </div>
      <div className="mb-4">
        <button
          className="px-20 py-2 border-2 border-primary-color text-[1rem] font-bold bg-white text-primary-color hover:bg-primary-color hover:text-white"
          onClick={onRegisterButtonClicked}
          disabled={
            studentInfo.name === "" ||
            studentInfo.confirmPassword === "" ||
            studentInfo.email === "" ||
            studentInfo.password === ""
          }
        >
          Register
        </button>
      </div>
      <div>
        <h1 className="text-[.8rem]">
          Already have an account?{" "}
          <span
            className="underline text-primary-color font-semibold cursor-pointer"
            onClick={onChangeComponentButtonClicked}
          >
            Login
          </span>{" "}
          here.
        </h1>
      </div>
    </div>
  );
};

export default Register;
