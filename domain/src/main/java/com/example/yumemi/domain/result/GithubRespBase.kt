package com.example.yumemi.domain.result

import com.example.yumemi.domain.model.Contributor

/**
 * Classic sealed class hierarchy for dealing with both happy and fail flows
 */
sealed class GithubRespBase

data class GithubRespOK(
    val contributors: List<Contributor>
): GithubRespBase()

data class GithubRespFail(
    val msg: String
): GithubRespBase()
