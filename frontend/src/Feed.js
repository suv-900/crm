import React from "react";
import { useEffect } from "react";
import { Link } from "react-router-dom";

export default function Feed(){

    useEffect(()=>{
       fetchPosts(); 
    },[])

    async function fetchPosts(){

        const res = await fetch("http://localhost:8080/post/getFeedPosts",{
            method:"GET"
        })

        const resJSON = await res.json();
        console.log(resJSON);
        const postListDiv = document.getElementById("postsList");
        for(let i = 0;i<resJSON.size();i++){
            const post = resJSON[i];
            
            const postDiv = document.createElement("div");
            postDiv.className = "post";
            postDiv.innerHTML = <Link to={`/post/${post.id}`} >{post.title}</Link> 
           
            postListDiv.appendChild(postDiv);
        }
    }

    return(
        <div>
            <div id="postsList" className="postsList" ></div>
        </div>
    )
}