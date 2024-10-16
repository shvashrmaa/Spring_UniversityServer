import {combineReducers, configureStore} from '@reduxjs/toolkit'
import { thunk } from 'redux-thunk';
import UserReducer from './slices/UserSlice'

const rootReducer = combineReducers(
    {
        UserAuth : UserReducer,
    }
)

const ReduxStore = configureStore({
    reducer : rootReducer,
    middleware: (getDefaultMiddleware) => getDefaultMiddleware().concat(thunk)
})

export type AppDispatch = typeof ReduxStore.dispatch;
export type RootState = ReturnType<typeof ReduxStore.getState>

export default ReduxStore;