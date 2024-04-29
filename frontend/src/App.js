import React from "react";
import { Route, BrowserRouter, Routes } from "react-router-dom";
import ViewPost from "./posts/ViewPost";
import Feed from "./posts/Feed";
import Home from "./Home";
import AdminLogin from "./admin/AdminLogin";
import AdminRegister from "./admin/AdminRegister";
import UpdatePost from "./posts/UpdatePost";
import ViewAllPosts from "./posts/ViewAllPosts";

function App() {
  return (
    <BrowserRouter>
    <Routes>
        <Route path="/login" element={<AdminLogin/>} />
        <Route path="/register" element={<AdminRegister/>} />
        <Route path="/viewpost/:id" element={<ViewPost/>} />
        <Route path="/update_post/:id" element={<UpdatePost/>} />
        <Route path="/dashboard" element={<ViewAllPosts/>} />
    </Routes>
    </BrowserRouter>
  );
}

export default App;
