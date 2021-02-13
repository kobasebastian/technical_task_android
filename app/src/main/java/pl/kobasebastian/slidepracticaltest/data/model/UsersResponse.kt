package pl.kobasebastian.slidepracticaltest.data.model

import pl.kobasebastian.slidepracticaltest.data.model.Data
import pl.kobasebastian.slidepracticaltest.data.model.Meta

data class UsersResponse(
    val code: Int,
    val data: List<Data>,
    val meta: Meta
)