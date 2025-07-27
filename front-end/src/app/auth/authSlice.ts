import type { PayloadAction } from "@reduxjs/toolkit";
import { createSlice } from "@reduxjs/toolkit";
import type { RootState } from "../store";

type AuthState = {
  user: User | null;
  token: string | null;
  isAuthenticated: boolean;
}

type User = {
  id: string;
  firstName: string;
  lastName: string;
  email: string;
  createdAt: string
}

type CredentialsPayload = {
  token: string | null;
}

type UserPayload = {
  user: User;
}

const initialState: AuthState = {
  user: null,
  token: null,
  isAuthenticated: false
};

const authSlice = createSlice({
  name: 'auth',
  initialState,
  reducers: {
    setCredentials: (state, action: PayloadAction<CredentialsPayload>) => {
      const { token } = action.payload;
      state.token = token;
      state.isAuthenticated = true;
    },
    setUser: (state, action: PayloadAction<UserPayload >) => {
      const currentUser  = action.payload.user;
      state.user = currentUser;
    },
    setIsAuthenticated: (state, action: PayloadAction<boolean>) => {
      state.isAuthenticated = action.payload;
    },
    logOut: (state) => {
      state.user = null;
      state.token = null;
      state.isAuthenticated = false;
    }
  },
});


export const { setCredentials, setUser, logOut, setIsAuthenticated } = authSlice.actions;

export default authSlice.reducer;

export const selectCurrentUser = (state: RootState) => state.auth.user;
export const selectCurrentToken = (state: RootState) => state.auth.token;
export const selectIsAuthenticated = (state: RootState) => state.auth.isAuthenticated;