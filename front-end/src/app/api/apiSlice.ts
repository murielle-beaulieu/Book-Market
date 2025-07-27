import { createApi, fetchBaseQuery } from "@reduxjs/toolkit/query/react";
import type { store } from "../store";

const baseAuthQuery = fetchBaseQuery({
  baseUrl: "http://localhost:8080",
  credentials: "include",
  prepareHeaders: (headers, { getState }) => {
    const state = getState() as ReturnType<typeof store.getState>;
    const token = state.auth.token;
    if (token) {
      headers.set("authorization", `Bearer ${token}`);
    }
    return headers;
  },
});

export const apiSlice = createApi({
  baseQuery: baseAuthQuery,
  endpoints: () => ({}),
});