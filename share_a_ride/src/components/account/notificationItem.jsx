import React, {useState, useEffect} from 'react'
import {formatDate} from "../../reusable/utils"

const NotificationItem = (props) => {

    return (
        <div className="shadow bg-white rounded"
             style={{margin: '20px'}}>
            <div className="row">
                <img className="col-sm-3" style={{width: "70px", height: "70px"}}
                     src="/user.svg"/>


                #{props.data.id}

                <div className="row col-sm-9">
                    <div className="col-sm-6"
                        style={{textAlign: "left",fontWeight: "bold"}}>
                        {props.data.byUser.firstName+" "+props.data.byUser.lastName}
                    </div>
                    <div className="col-sm-6"
                        style={{ textAlign: "right"}}>
                        {formatDate(props.data.timeDate)}
                    </div>

                    <div className="row">
                        {props.data.description}
                    </div>
                </div>

            </div>

        </div>
    );
};

export default NotificationItem;