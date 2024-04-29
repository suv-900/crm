import React from "react";
import { Router,Route,Switch } from "react-router-dom";
import ViewPost from "./ViewPost";
import Feed from "./Feed";
import Home from "./Home";
import AdminLogin from "./admin/AdminLogin";
import AdminRegister from "./admin/AdminRegister";

function Crm() {
  return (
    <Router>
      <Switch>
        <Route path="/login" element={<AdminLogin/>} />
        <Route path="/register" element={<AdminRegister/>} />
        <Route path="/viewpost/:id" element={<AdminLogin/>} />

      </Switch>
    </Router>
  );
}

export default Crm;
