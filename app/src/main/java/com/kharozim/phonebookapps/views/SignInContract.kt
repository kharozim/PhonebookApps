package com.kharozim.phonebookapps.views

import com.kharozim.phonebookapps.remote.body.BodySignIn
import com.kharozim.phonebookapps.remote.model.SignInModel

class SignInContract {
    interface View {
        fun onSuccessSignIn(token : String)

    }

    interface Presenter {
        fun getSignIn(bodySignIn: BodySignIn)
    }
}