import {BrowserRouter as Router , Route , Routes} from 'react-router-dom'
import HomeScreen from './screens/HomeScreen'
import AuthScreen from './screens/AuthScreen'
import ProfileScreen from './screens/ProfileScreen'

function App() {
  return (
    <>
        <Router>
            <Routes>
                <Route path="/" element={<HomeScreen />} />
                <Route path='/authentication' element={<AuthScreen />} />
                <Route path='/profile' element={<ProfileScreen />} />
            </Routes>
        </Router>
    </>
  )
}

export default App
