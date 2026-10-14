using MeuAppMaui.Models;

namespace MeuAppMaui.Services;

public interface IApiService
{
    Task<Item> ObterItemAsync(int id);
}

public class ApiServiceMock : IApiService
{
    public async Task<Item> ObterItemAsync(int id)
    {
        await Task.Delay(500); 
        return new Item { Id = id, Name = "Exemplo", Description = "Mock via serviço" };
    }
}