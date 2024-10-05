import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import App from './App.tsx'
import './index.css'
import {Provider} from 'react-redux'
import ReduxStore from './redux/Redux.tsx'

createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <Provider store={ReduxStore}>
    <App />
    </Provider>
  </StrictMode>,
)
