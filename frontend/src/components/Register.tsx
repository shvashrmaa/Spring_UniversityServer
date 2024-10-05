import React from "react";

interface ChildComponentProps{
    setComponent:React.Dispatch<React.SetStateAction<string>>
}

const Register:React.FC<ChildComponentProps> = ({setComponent}) => {
    const onChangeComponentButtonClicked = () =>
    {
        setComponent("Login");
    }
  return (
    <div className="w-[100%] bg-white rounded-md flex items-center justify-center flex-col">
      <h1 className="font-bold text-orange-500 text-[1.3rem] underline cursor-pointer mb-6">
        Register Acocunt
      </h1>
      <form className="flex flex-col m-3 w-[100%] mb-4">
        <label htmlFor="nameInput" className="font-semibold text-gray-600 mb-1">
          Full Name
        </label>
        <input
          type="text"
          name="nameInput"
          id="nameInput"
          required
          className="py-1 font-semibold text-gray-700 border-b-2 border-gray-300 outline-none mb-2"
        />
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
        <label
          htmlFor="confirmPasswordInput"
          className="font-semibold text-gray-600 mb-1"
        >
          Confirm Password
        </label>
        <input
          type="password"
          name="confirmPasswordInput"
          id="confirmPasswordInput"
          required
          className="py-1 font-semibold text-gray-700 border-b-2 border-gray-300 outline-none mb-2"
        />
      </form>
      <div className="flex flex-row justify-center items-center space-x-2 m-4">
        <input type="checkbox" name="TermsCheckbox" id="TermsCheckbox" required/>
        <label
          htmlFor="TermsCheckbox"
          className="font-semibold text-[.8rem] text-gray-600 mb-1"
        >
          By clicking this you accepted all terms & conditions
        </label>
      </div>
      <div className="mb-4">
        <button className="px-20 py-2 border-2 border-orange-500 text-[1rem] font-bold bg-white text-orange-500 hover:bg-orange-500 hover:text-white">
          Register
        </button>
      </div>
      <div>
        <h1 className="text-[.8rem]">
          Already have an account?{" "}
          <span className="underline text-orange-500 font-semibold cursor-pointer" onClick={onChangeComponentButtonClicked}>
            Login
          </span>{" "}
          here.
        </h1>
      </div>
    </div>
  );
};

export default Register;
