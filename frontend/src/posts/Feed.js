import React, { useState } from "react";
import { useEffect } from "react";

export default function Feed(){
    const[page,setPage] = useState(0);
    const[loading,setLoading] = useState(false);
    
    useEffect(()=>{
        setTimeout(()=>{
            fetchPosts(); 
        },3000)
    },[])

    async function fetchPosts(){
        if(loading) return;

        setLoading(true); 
        const res = await fetch(`http://localhost:8080/post/getFeedPosts?offset=${page*5}`,{
            method:"GET"
        })

        const resJSON = await res.json();
        console.log(resJSON);
        setLoading(false);
        const postListDiv = document.getElementById("postsList");
        for(let i = 0;i<resJSON.size();i++){
            const post = resJSON[i];
            
            const postDiv = document.createElement("a");
            postDiv.className = "post-link";
            postDiv.href = `http://localhost:3000/viewpost?id=${post.id}` 
           
            postListDiv.appendChild(postDiv);
        }
        setPage(page+1);
    }

    return(
        <div>
            <div id="postsList" className="postsList" ></div>
            {loading && <p>Loading...</p>}
        </div>
    )
}