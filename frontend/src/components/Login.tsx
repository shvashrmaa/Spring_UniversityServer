import React from "react";

interface ChildComponentProps {
  setComponent: React.Dispatch<React.SetStateAction<string>>;
}

const Login: React.FC<ChildComponentProps> = ({ setComponent }) => {
    const onChangeComponentButtonClicked = () =>
        {
            setComponent("Register");
        }
  return (
    <div className="w-[100%] bg-white rounded-md flex items-center justify-center flex-col">
      <h1 className="font-bold text-primary-color text-[1.3rem] underline cursor-pointer mb-6">
        Login Acocunt
      </h1>
      <form className="flex flex-col m-3 w-[100%] mb-4">
        <label
          htmlFor="emailInput"
          className="font-semibold text-gray-600 mb-1"
        >
          Email Address
        </label>
        <input
          type="email"
          name="emailInput"
          id="emailInput"
          required
          className="py-1 font-semibold text-gray-700 border-b-2 border-gray-300 outline-none mb-2"
        />
        <label
          htmlFor="passwordInput"
          className="font-semibold text-gray-600 mb-1"
        >
          Password
        </label>
        <input
          type="password"
          name="passwordInput"
          id="passwordInput"
          required
          className="py-1 font-semibold text-gray-700 border-b-2 border-gray-300 outline-none mb-2"
        />
      </form>
      <div className="mb-4">
        <button className="px-20 py-2 border-2 border-primary-color text-[1rem] font-bold bg-white text-primary-color hover:bg-primary-color hover:text-white">
          Login
        </button>
      </div>
      <div>
        <h1 className="text-[.8rem]">
          Don't have an account?
          <span className="underline text-primary-color font-semibold cursor-pointer" onClick={onChangeComponentButtonClicked}>
            Register
          </span>{" "}
          here.
        </h1>
      </div>
    </div>
  );
};

export default Login;
