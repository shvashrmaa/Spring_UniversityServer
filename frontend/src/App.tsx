import {BrowserRouter as Router , Route , Routes} from 'react-router-dom'
import HomeScreen from './screens/HomeScreen'
import AuthScreen from './screens/AuthScreen'

function App() {
  return (
    <>
        <Router>
            <Routes>
                <Route path="/" element={<HomeScreen />} />
                <Route path='/authentication' element={<AuthScreen />} />
            </Routes>
        </Router>
    </>
  )
}

export default App
