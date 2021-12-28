package coursework.gi.kishlish.ui.models

data class UserModel(
    val id: String = "",
    var username: String = "",
    var bio: String = "",
    var fullname: String = "",
    var status: String = "",
    var email: String = "",
    var photoUrl: String = "empty"
)