import React, { useEffect } from 'react'
import { useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { RootState } from '../redux/Redux';
import Navbar from '../components/Navbar';

const HomeScreen:React.FC = () => {
    const UserAuthState = useSelector((state:RootState) => state.UserAuth);
    const {token} = UserAuthState;
    const navigate = useNavigate();

    useEffect(() => {
        if(token === null) navigate("/authentication")
    }, [navigate, token])

    useEffect(() =>
    {
        console.log(token)
    },[token])
    
  return (
    <div className='h-[100vh] w-[100vw] flex flex-col'>
        <Navbar />
        <div>Center body</div>
        <footer></footer>
    </div>
  )
}

export default HomeScreen;