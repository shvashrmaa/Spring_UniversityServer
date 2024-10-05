import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { AppDispatch, RootState } from "../redux/Redux";
import { getStudentDetails } from "../redux/slices/StudentSlice";

const ProfileScreen = () => {
  const dispatch: AppDispatch = useDispatch();
  const token = useSelector((state: RootState) => state.StudentAuth.token);
  const { user } = useSelector((state: RootState) => state.StudentAuth);

  useEffect(() => {
    if (token) dispatch(getStudentDetails(token));
  }, [dispatch, token]);

  return <div>user : {user}</div>;
};

export default ProfileScreen;
