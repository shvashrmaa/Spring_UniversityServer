import { AiOutlineUser } from "react-icons/ai";
import ManipalLogo from "../assets/images/ManipalLogo.jpg";
import { Menu, MenuButton, MenuItem, MenuItems } from "@headlessui/react";
import { Link } from "react-router-dom";
import { useDispatch } from "react-redux";
import { AppDispatch } from "../redux/Redux";
import { clearToken } from "../redux/slices/UserSlice";

const Navbar = () => {
    const dispatch:AppDispatch = useDispatch()

    const onSignOutbuttonClicked = (e:React.FormEvent) =>
    {
        e.preventDefault();
        dispatch(clearToken());
    }
    
  return (
    <div className="w-[100%] bg-primary-color justify-between flex px-10 py-5">
      <div>
        <img
          src={ManipalLogo}
          alt="Manipal Logo"
          className="mix-blend-color-burn w-[12rem] object-fill cursor-pointer"
        />
      </div>
      <div>
        <Menu as="div" className="relative inline-block text-left">
          <div>
            <MenuButton>
              <AiOutlineUser className="text-3xl cursor-pointer" />
            </MenuButton>
          </div>

          <MenuItems
            transition
            className="absolute right-0 z-10 mt-2 w-56 origin-top-right rounded-md bg-white shadow-lg ring-1 ring-black ring-opacity-5 transition focus:outline-none data-[closed]:scale-95 data-[closed]:transform data-[closed]:opacity-0 data-[enter]:duration-100 data-[leave]:duration-75 data-[enter]:ease-out data-[leave]:ease-in"
          >
            <div className="py-1">
              <MenuItem>
                <Link
                  to={'/profile'}
                  className="block px-4 py-2 text-sm text-gray-700 data-[focus]:bg-gray-100 data-[focus]:text-gray-900"
                >
                  Profile
                </Link>
              </MenuItem>
                <MenuItem>
                  <button
                    type="submit"
                    className="block w-full px-4 py-2 text-left text-sm text-gray-700 data-[focus]:bg-gray-100 data-[focus]:text-gray-900"
                    onClick={onSignOutbuttonClicked}
                  >
                    Sign out
                  </button>
                </MenuItem>
            </div>
          </MenuItems>
        </Menu>
      </div>
    </div>
  );
};

export default Navbar;
