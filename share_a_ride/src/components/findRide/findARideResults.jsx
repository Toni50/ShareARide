import React, {useState, useEffect} from 'react'
import 'date-fns';
import FindARideResultsItem from "./findARideResultsItem";
import {Redirect} from "react-router";
import RepositoryService from "../../custom-axios/repository";


const FindARideResults = (props) => {

    const [ridesFound, setRidesFound] = useState([]);

    useEffect(() => {
        RepositoryService.findARide(props.data.location.state)
            .then((response) => {
                console.log("findaride response");
                console.log(response);

                setRidesFound(response.data)
            }).catch(RepositoryService.handleServerError);
    }, []);




    function showData() {
        {/*console.log("FindARideResults props");
        console.log(props.data.location.state);*/}


        let content=[];
        if (props.data.location.state == null ||
            props.data.location.state=={}) {
            return <Redirect to={{
                pathname: '/findaride'
            }}/>;
        }



        content =ridesFound.map((item, index) => {
            return (
                <div>
                    <FindARideResultsItem
                        data={item}
                    />
                </div>
            )
        });
        return (
            <div>
                {content}
            </div>
        )
    }

    return (
        <div>
            <h1>Results</h1>
            {showData()}
        </div>
    );
};


export default FindARideResults;