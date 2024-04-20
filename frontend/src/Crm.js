import React from "react";
import { Router,Route,Switch } from "react-router-dom";
import ViewPost from "./ViewPost";
import Feed from "./Feed";
import Home from "./Home";

function Crm() {
  return (
    <Router>
      <Switch>
        <Route path="/">
          <Home/>
        </Route>

        <Route path="/post/:id">
          <ViewPost/>
        </Route>

        <Route path="/feed">
          <Feed/>
        </Route>
      </Switch>
    </Router>
  );
}

export default Crm;
