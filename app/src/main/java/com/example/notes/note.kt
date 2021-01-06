package com.example.notes

import io.realm.RealmObject


open class note(
    var text: String?= null,
    var id: Int?= null
) : RealmObject(){}