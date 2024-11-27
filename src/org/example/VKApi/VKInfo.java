package org.example.vkApi;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.users.Fields;
import com.vk.api.sdk.objects.users.UserFull;
import org.example.VKApi.Credentials;

import java.util.List;

public class VKInfo {
    private static final String ACCESS_TOKEN = Credentials.ACCESS_TOKEN;
    private static final long APP_ID = Credentials.APP_ID;
    private final VkApiClient vk;
    private final UserActor actor;

    public VKInfo() {
        TransportClient transportClient = new HttpTransportClient();
        vk = new VkApiClient(transportClient);
        actor = new UserActor(APP_ID, ACCESS_TOKEN);
    }

    public UserFull getUserByName(String name) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<UserFull> users = null;

        try {
            users = vk.users().search(actor)
                    .q(name) // Поиск по имени
                    .count(1) // Ограничение результата 1 пользователем
                    .fields(Fields.SEX, Fields.BDATE, Fields.CITY, Fields.STATUS) // Запрашиваем нужные поля
                    .execute()
                    .getItems();
        } catch (ApiException | ClientException e) {
            e.printStackTrace();
            return null;
        }

        if (users.isEmpty()) {
            return null;
        }

        return users.get(0);
    }

    public void printUserInfo(UserFull user) {
        if (user == null) {
            System.out.println("Пользователь не найден");
            return;
        }

        System.out.println("ID VK: " + user.getId());

        String gender = (user.getSex() != null)
                ? (user.getSex() == com.vk.api.sdk.objects.base.Sex.FEMALE ? "Женский" : "Мужской")
                : "Не указан";
        System.out.println("SEX: " + gender);

        String city = (user.getCity() != null) ? user.getCity().getTitle() : "Не указан";
        System.out.println("CITY: " + city);

        String birthDate = (user.getBdate() != null) ? user.getBdate() : "Не указана";
        System.out.println("B-DAY: " + birthDate);
    }
}

