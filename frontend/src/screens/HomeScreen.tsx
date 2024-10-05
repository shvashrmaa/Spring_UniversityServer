import React, { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom';

const HomeScreen = () => {
    const [userId, setuserId] = useState(null);
    const navigate = useNavigate();
    useEffect(() => {
        if(userId == null) navigate("/authentication")
    }, [])
    
  return (
    <div>This is HomeScreen</div>
  )
}

export default HomeScreen