package com.andrey7mel.testrx.model.dataprovider;

import com.andrey7mel.testrx.model.api.ApiInterface;
import com.andrey7mel.testrx.model.api.ApiModule;

/**
 * Created by Сергей_де on 25.11.2015.
 */
public class BaseDataProvider {
    ApiInterface apiInterface = ApiModule.getApiInterface();

}
