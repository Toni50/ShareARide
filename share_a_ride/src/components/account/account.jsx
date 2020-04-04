import React, {useState, useEffect} from 'react'
import 'date-fns';
import RepositoryService from "../../custom-axios/repository";

const Account = (props) => {

    const [userInfo, setUserInfo] = React.useState({});

    useEffect(() => {

        RepositoryService.getAccountInfo()
            .then((response) => {
                console.log(response);
                if (response.status === 200) {
                    setUserInfo(response.data)
                }
            }).catch(RepositoryService.handleServerError);

    }, []);


    function handleUserInfoChanged(e) {
        let attrName = e.target.name;
        let attrVal = e.target.value;

        if (attrName === 'phoneNumber') {
            if (attrVal.length > 9) {
                return
            }
        }

        setUserInfo((prevState) => {
            return {...prevState, [attrName]: attrVal}
        })
    }

    function UpdateInfo() {
        RepositoryService.updateUser(userInfo)
            .then((response) => {
                alert("updateUser")
                console.log(response);
                setUserInfo(response.data)
            }).catch(RepositoryService.handleServerError);
    }

    function showView() {
        return (
            <div className="row">
                <img className="col-sm-3" style={{width: "80px", height: "80px"}}
                     src="/user.svg"/>
                <div className="col-sm-9">

                    <input className="form-control"
                           type="text"
                           disabled={true}
                           name={"email"}
                           value={userInfo.email}
                           onChange={handleUserInfoChanged}
                    />

                    <input className="form-control"
                           type="text"
                           name={"firstName"}
                           value={userInfo.firstName}
                           onChange={handleUserInfoChanged}
                    />

                    <input className="form-control"
                           type="text"
                           name={"lastName"}
                           value={userInfo.lastName}
                           onChange={handleUserInfoChanged}
                    />

                    <input className="form-control"
                           type="password"
                           name={"password"}
                           value={userInfo.password}
                           onChange={handleUserInfoChanged}
                    />

                    <input className="form-control"
                           type="text"
                           name={"phoneNumber"}
                           value={userInfo.phoneNumber}
                           onChange={handleUserInfoChanged}
                    />

                    <input className="form-control"
                           type="text"
                           name={"homeTown"}
                           value={userInfo.homeTown}
                           onChange={handleUserInfoChanged}
                    />

                    <input className="form-control"
                           type="text"
                           name={"country"}
                           value={userInfo.country}
                           onChange={handleUserInfoChanged}
                    />
                </div>

                <button className="btn btn-primary"
                        onClick={UpdateInfo}>
                    Update Account info
                </button>
            </div>
        )
    }

    return (
        <div>
            {showView()}

        </div>
    );


};


export default Account;